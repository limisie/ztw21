<?php

class AA_Plugin_Admin {

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

	public function aa_admin_actions_register_menu(){
		add_options_page("Admin Announcements Plugin", "Admin Announcements Plugin", 'manage_options', "aa_plugin", array( $this, 'aa_admin_page'));
	}

	function connect_to_db() {
		$link = mysqli_connect("localhost", "root", null, "wordpressDB");
		if (mysqli_connect_errno()) die(mysqli_error($link));
		return $link;
	}

	public function add_annoucement( $new_announcemenet ) {
		$link = $this->connect_to_db();
		$result = mysqli_query($link, "INSERT INTO aa_plugin_announcements (content) VALUES ('$new_announcemenet');");
    	if (!$result) die(mysqli_error($link));
    	mysqli_close($link);
	}

	public function delete_announcements( $ids ) {
		$link = $this->connect_to_db();
		$result = mysqli_query($link, "DELETE FROM aa_plugin_announcements WHERE ID IN($ids)");
		if (!$result) die(mysqli_error($link));
		mysqli_close($link);
	}

	public function aa_admin_page() {
		require_once plugin_dir_path( dirname( __FILE__ ) ) . 'admin/index.php';
	}
}
?>