<?php
/**
 * Created by PhpStorm.
 * UserDAO: PONTES_STEFANE-ESIG
 * Date: 30.01.2019
 * Time: 17:34
 */

spl_autoload_register(
    function ($class_name) {
        require_once("/helpers/".$class_name.".php");
    });