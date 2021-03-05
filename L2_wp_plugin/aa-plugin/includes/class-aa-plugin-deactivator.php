<?php

/**
 * @link              https://github.com/limisie/aap
 * @since             1.0.0
 * @package           aa-plugin
 * @subpackage 		  aa-plugin/includes
 * @author     		  Kaja Limisiewicz
 */

class AA_Plugin_Deactivator {

	public static function deactivate() {
		$link = mysqli_connect("localhost", "root", null, "wordpressDB");
    	if (mysqli_connect_errno()) die(mysqli_error($link));

		$new_table = mysqli_query($link, "DROP TABLE aa_plugin_announcements");
    	if (!$new_table) die(mysqli_error($link));
		mysqli_close($link);
	}

}
