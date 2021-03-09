<?php

class AA_Plugin_Public {

	/**
	 * The ID of this plugin.
	 *
	 * @access   private
	 * @var      string    $plugin_name    The ID of this plugin.
	 */
	private $plugin_name;

	/**
	 * The version of this plugin.
	 *
	 * @access   private
	 * @var      string    $version    The current version of this plugin.
	 */
	private $version;

	/**
	 * The array of admin announcements.
	 *
	 * @access   private
	 * @var      array    $announcements    The array of admin announcements.
	 */
	private $announcements;

	/**
	 * Initialize the class and set its properties.
	 *
	 * @param      string    $plugin_name       The name of this plugin.
	 * @param      string    $version    The version of this plugin.
	 */
	public function __construct( $plugin_name, $version, $announcements ) {

		$this->plugin_name = $plugin_name;
		$this->version = $version;
		$this->announcements = $announcements;
	}

	/**
	 * Register the stylesheets for the public-facing side of the site.
	 */
	public function enqueue_styles() {
		wp_enqueue_style( $this->plugin_name, plugin_dir_url( __FILE__ ) . 'css/aa-plugin-public.css', array(), $this->version, 'all' );
	}

	function connect_to_db() {
		$link = mysqli_connect("localhost", "root", null, "wordpressDB");
		if (mysqli_connect_errno()) die(mysqli_error($link));
		return $link;
	}

	public function delete_announcement( $id ) {
		$link = $this->connect_to_db();
		$result = mysqli_query($link, "DELETE FROM aa_plugin_announcements WHERE ID = $id;");
		if (!$result) die(mysqli_error($link));
		mysqli_close($link);
	}

	private function get_random_announcement_html() {
		$random_index = mt_rand( 0, count($this->announcements) - 1);
		$random_announcement = "<p>" . $this->announcements[ $random_index ][1] . "</p>";
		$random_announcement_id = $this->announcements[ $random_index ][0];
		if ( current_user_can( 'administrator' )) {
			$admin_extra = "<form name='aa_plugin_announcement_id_form' method='POST'>
			<input type='hidden' name='aa_plugin_announcement_id' value='$random_announcement_id'>
			<p><input type='submit' class='aa_plugin_admin_button' value='Delete'></p></form>";
			return htmlspecialchars_decode("<div class='announcement'>" . $random_announcement . $admin_extra . "</div>");
		}
		return htmlspecialchars_decode("<div class='announcement'>" . $random_announcement . "</div>");
	}

	public function add_announcement_to_post( $content ) {
		$custom_content = $this->get_random_announcement_html();
    	$custom_content .= $content;
    	return $custom_content;
	}

	public function add_button_for_admin() {
		require_once plugin_dir_path( dirname( __FILE__ ) ) . 'public/index.php';
	}

}
