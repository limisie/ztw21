<?php

/**
 * Admin Announcements Plugin
 *
 * @link              https://github.com/limisie/aap
 * @package           aa-plugin
 *
 * @wordpress-plugin
 * Plugin Name:       Admin Announcements Plugin
 * Plugin URI:        https://github.com/limisie/aap
 * Description:       Admin Announcements Plugin lets you add random announcement before your posts.
 * Version:           1.0.0
 * Author:            Kaja Limisiewicz
 * Author URI:        https://github.com/limisie/
 * Text Domain:       aa-plugin
 */

if ( ! defined( 'WPINC' ) ) {
	die;
}

define( 'AA_PLUGIN_VERSION', '1.0.0' );

function activate_aa_plugin() {
	require_once plugin_dir_path( __FILE__ ) . 'includes/class-aa-plugin-activator.php';
	AA_Plugin_Activator::activate();
}

function deactivate_aa_plugin() {
	require_once plugin_dir_path( __FILE__ ) . 'includes/class-aa-plugin-deactivator.php';
	AA_Plugin_Deactivator::deactivate();
}

register_activation_hook( __FILE__, 'activate_aa_plugin' );
register_deactivation_hook( __FILE__, 'deactivate_aa_plugin' );

/**
 * Begins execution of the plugin.
 */
function run_aa_plugin() {

	require_once plugin_dir_path( dirname( __FILE__ ) ) . 'aa-plugin/includes/class-aa-plugin.php';

	$plugin = new AA_Plugin();
	$plugin->run();

}
run_aa_plugin();
