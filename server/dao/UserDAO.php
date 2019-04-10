<?php
require_once("DAO.php");

class UserDAO extends DAO
{
    public function __construct()
    {
        parent::__construct();
        $this->table = "user";
    }
}