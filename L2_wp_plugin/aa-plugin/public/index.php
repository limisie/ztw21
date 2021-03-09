<?php
    if(isset($_POST['aa_plugin_announcement_id'])) {
        $this->delete_announcement($_POST['aa_plugin_announcement_id']);
        print "<div class='notice'><p>Announcement deleted</p></div>";
    }
?>