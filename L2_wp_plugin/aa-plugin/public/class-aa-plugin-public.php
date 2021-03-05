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

	private function get_random_announcement_html() {
		return "<p class='announcement'>" . htmlspecialchars_decode($this->announcements[ mt_rand( 0, count($this->announcements) - 1) ][1]) . "</p>";
	}

	public function add_announcement_to_post( $content ) {
		$custom_content = $this->get_random_announcement_html();
    	$custom_content .= $content;
    	return $custom_content;
	}

}
