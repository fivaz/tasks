<?php
/**
 * Created by PhpStorm.
 * User: PONTES_STEFANE-ESIG
 * Date: 30.01.2019
 * Time: 17:34
 */

spl_autoload_register(

    function ($class_name) {

        $classFile = $class_name . ".php";

//        $files = glob("../../php/*");
        $files = glob("*");

        $found = false;

        $index = 0;

        while ($index < count($files) && !$found) {

            $fullPath = $files[$index] . "/" . $classFile;

            if (file_exists($fullPath)) {

                $found = true;
                require_once($fullPath);
            }
            $index++;
        }

        if (!$found)
            die("ERROR Importing file : " . $classFile);
    });