<?php

/**
 * @link              https://github.com/limisie/aap
 * @package           aa-plugin
 * @subpackage 		  aa-plugin/includes
 * @author     		  Kaja Limisiewicz
 */

class AA_Plugin_Activator {

	public static function activate() {
		$link = mysqli_connect("localhost", "root", null, "wordpressDB");
    	if (mysqli_connect_errno()) die(mysqli_error($link));

		$new_table = mysqli_query($link, "CREATE TABLE aa_plugin_announcements (
											id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
											content VARCHAR(500) NOT NULL)");
    	if (!$new_table) die(mysqli_error($link));
		mysqli_close($link);
	}

}
