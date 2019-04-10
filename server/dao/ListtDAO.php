<?php
require_once("DAO.php");

class ListtDAO extends DAO
{
    public function __construct()
    {
        parent::__construct();
        $this->table = "listt";
    }
}