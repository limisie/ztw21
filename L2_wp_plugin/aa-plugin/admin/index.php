<div class="wrap">
<h1 class="wp-heading-inline">Admin Announcements Plugin</h1>
<hr class="wp-header-end">

<?php
if(isset($_POST["new_announcement"])){
    $new_announcemenet = $_POST["new_announcement"];
    $this->add_annoucement( $new_announcemenet );
    show_success_notice('Announcement saved.');
}

if(isset($_POST["announcements"])){
    $ids_to_delete = $_POST["announcements"];
    $ids_to_delete = implode( ', ', $ids_to_delete );
    $this->delete_announcements( $ids_to_delete );
    show_success_notice('Announcement deleted');
}
?>

<div class="wrap">
    <div class="tablenav top">
        <div class="alignleft actions">
        <form method="post">
            <select name="actions" id="actions">
                <option value="add_announcement">Add new admin announcement</option>
                <option value="manage_announcements">List and delete your announcements</option>
            </select>
            <input type="submit" class="button action" value="Apply">
        </form>
        </div>
        <div class="tablenav-pages one-page">
            <span class="displaying-num">.</span>
        </div>
    </div>

<?php

if(isset($_POST["actions"])){
    switch ($_POST["actions"]) {
        case "add_announcement":
            add_announcement_form();
            break;
        case "manage_announcements":
            list_announcements_form( $this->announcements );
            break;
    }
}

function list_announcements_form( $array ) {
    print '<div></div><form name="deleting_announcements_form" method="post">';
    print '<h3>Current Admin Announcements</h3>';

    print '<table class="wp-list-table widefat fixed striped table-view-list">';
    print '<thead><tr><td class="check-column"></td><th id="content" class="sortable desc"><span><strong>Conent of the announcement</strong></span></th></tr></thead>';
    print '<tbody>';
    foreach( $array as $announcement ) {
        print '<tr><th class="check-column"><input type="checkbox" name="announcements[]" id="announcement' . $announcement[0] . '" value="' . $announcement[0] . '"></th>';
        print '<td><label for="announcement' . $announcement[0] . '">' . $announcement[1] . '</label></td></tr>';
    }
    print '</tbody></table>';
    print '<div class="tablenav bottom"><div class="alignleft actions bulkactions"><input type="submit" class="button action" value="Delete"></div></div></form></div>';
}

function add_announcement_form() {
    print '<form name="new_announcement_form" method="post">';
    print '<h3>Add a brand new Admin Announcement</h3>';
    print '<p>Type in new Admin Announcement:</p>';
    print '<textarea name="new_announcement" rows="3" cols="100"></textarea>';
    print '<p><input type="submit" class="button action" value="Submit"></p></form>';
}

function show_success_notice( $message ) {
    print "<div class='notice notice-success is- dismissible'><p>$message</p></div>";
}

?>

</div>