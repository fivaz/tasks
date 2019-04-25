<?php
define("DB_DRIVE", "mysql");
define("DB_HOST", "localhost");
define("DB_PORT", "3306");
define("DB_NAME", "mydb");
define("DB_CHARSET", "utf8");
define("DB_USERNAME", "root");
define("DB_PASSWORD", "");

class DBConnection extends PDO
{
    public function __construct()
    {
        return parent::__construct(DB_DRIVE.":host=".DB_HOST.";port=".DB_PORT.";dbname=".DB_NAME.";charset=".DB_CHARSET, DB_USERNAME, DB_PASSWORD);
    }
}