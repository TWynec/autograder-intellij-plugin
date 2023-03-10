<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $uploaded_file = array_values($_FILES)[0];
    if ($uploaded_file['error'] === UPLOAD_ERR_OK) {
        $java_file = file_get_contents($uploaded_file['tmp_name']);
        //echo $java_file;
        $output = exec("/usr/bin/java -jar MyJarFile.jar");
        echo $output;
    } else {
        echo 'Error uploading file';
    }
}
?>
