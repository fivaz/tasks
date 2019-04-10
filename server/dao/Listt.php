<?php
require_once("DAO.php");

class Listt extends DAO
{
    public function __construct()
    {
        parent::__construct();
        $this->table = "listt";
    }
}