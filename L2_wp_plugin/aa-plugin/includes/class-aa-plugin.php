<?php

/**
 * @link              https://github.com/limisie/aap
 * @since             1.0.0
 * @package           aa-plugin
 * @subpackage 		  aa-plugin/includes
 * @author     		  Kaja Limisiewicz
 */

/**
 * The core plugin class.
 *
 * This is used to define admin-specific hooks and public-facing site hooks.
 *
 * Also maintains the unique identifier of this plugin as well as the current
 * version of the plugin.
 *
 * @package           aa-plugin
 * @subpackage 		  aa-plugin/includes
 */
class AA_Plugin {

	/**
	 * The loader that's responsible for maintaining and registering all hooks that power
	 * the plugin.
	 *
	 * @access   protected
	 * @var      AA_Plugin_Loader    $loader    Maintains and registers all hooks for the plugin.
	 */
	protected $loader;

	/**
	 * The unique identifier of this plugin.
	 *
	 * @access   protected
	 * @var      string    $plugin_name    The string used to uniquely identify this plugin.
	 */
	protected $plugin_name;

	/**
	 * The current version of the plugin.
	 *
	 * @access   protected
	 * @var      string    $version    The current version of the plugin.
	 */
	protected $version;

	/**
	 * The announcement array.
	 *
	 * @access   protected
	 * @var      array    $announcements    The announcement array.
	 */
	protected $announcements;

	/**
	 * Define the core functionality of the plugin.
	 *
	 * Set the plugin name and the plugin version that can be used throughout the plugin.
	 * Load the dependencies, define the locale, and set the hooks for the admin area and
	 * the public-facing side of the site.
	 *
	 */
	public function __construct() {
		if ( defined( 'AA_PLUGIN_VERSION' ) ) {
			$this->version = AA_PLUGIN_VERSION;
		} else {
			$this->version = '1.0.0';
		}
		$this->plugin_name = 'aa-plugin';

		$this->load_dependencies();

		$this->announcements = $this->get_data_from_database();

		$this->define_admin_hooks();
		$this->define_public_hooks();
	}

	/**
	 * Load the required dependencies for this plugin.
	 *
	 * Include the following files that make up the plugin:
	 *
	 * - AA_Plugin_Loader. Orchestrates the hooks of the plugin.
	 * - AA_Plugin_Admin. Defines all hooks for the admin area.
	 * - AA_Plugin_Public. Defines all hooks for the public side of the site.
	 *
	 * Create an instance of the loader which will be used to register the hooks
	 * with WordPress.
	 *
	 * @access   private
	 */
	private function load_dependencies() {

		require_once plugin_dir_path( dirname( __FILE__ ) ) . 'includes/class-aa-plugin-loader.php';
		require_once plugin_dir_path( dirname( __FILE__ ) ) . 'admin/class-aa-plugin-admin.php';
		require_once plugin_dir_path( dirname( __FILE__ ) ) . 'public/class-aa-plugin-public.php';

		$this->loader = new AA_Plugin_Loader();
	}

	/**
	 * Register all of the hooks related to the admin area functionality
	 * of the plugin.
	 *
	 * @access   private
	 */
	private function define_admin_hooks() {

		$plugin_admin = new AA_Plugin_Admin( $this->get_plugin_name(), $this->get_version(), $this->announcements );
		$this->loader->add_action('admin_menu', $plugin_admin, 'aa_admin_actions_register_menu');
	}

	/**
	 * Register all of the hooks related to the public-facing functionality
	 * of the plugin.
	 *
	 * @access   private
	 */
	private function define_public_hooks() {

		$plugin_public = new AA_Plugin_Public( $this->get_plugin_name(), $this->get_version(), $this->announcements );

		$this->loader->add_action( 'wp_enqueue_scripts', $plugin_public, 'enqueue_styles' );
		$this->loader->add_filter('the_content', $plugin_public, 'add_announcement_to_post');
	}

	/**
	 * Get announcement data from the database.
	 *
	 * @access   private
	 */
	private function get_data_from_database() {
		$link = mysqli_connect("localhost", "root", null, "wordpressDB");
    	if (mysqli_connect_errno()) die(mysqli_error($link));

		$announcements = mysqli_query($link, "SELECT * FROM aa_plugin_announcements;");
    	if (!$announcements) die(mysqli_error($link));

		mysqli_close($link);

		return mysqli_fetch_all($announcements);
	}


	/**
	 * Run the loader to execute all of the hooks with WordPress.
	 */
	public function run() {
		$this->loader->run();
	}

	/**
	 * The name of the plugin used to uniquely identify it within the context of
	 * WordPress and to define internationalization functionality.
	 *
	 * @return    string    The name of the plugin.
	 */
	public function get_plugin_name() {
		return $this->plugin_name;
	}

	/**
	 * The reference to the class that orchestrates the hooks with the plugin.
	 *
	 * @return    AA_Plugin_Loader    Orchestrates the hooks of the plugin.
	 */
	public function get_loader() {
		return $this->loader;
	}

	/**
	 * Retrieve the version number of the plugin.
	 *
	 * @return    string    The version number of the plugin.
	 */
	public function get_version() {
		return $this->version;
	}

}
