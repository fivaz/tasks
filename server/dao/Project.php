<?php
require_once("DAO.php");

class Project extends DAO
{
    public function __construct()
    {
        parent::__construct();
        $this->table = "project";
    }
}