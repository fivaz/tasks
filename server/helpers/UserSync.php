<?php

require_once("../dao/UserDAO.php");

class UserSync
{
    static function sync($array){
        $userDAO = new UserDAO();
        $userDAO->save($array);
    }
}