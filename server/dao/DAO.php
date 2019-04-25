<?php
/**
 * Created by PhpStorm.
 * UserDAO: Fivaz
 * Date: 05/02/2019
 * Time: 03:55
 */

//require_once("DBConnection.php");

class DAO
{
    protected $db;
    protected $table;

    public function __construct()
    {
        $this->db = new DBConnection();
    }

    public function findAll($fk = null, $id = null)
    {
        if ($fk)
            $resultSQL = $this->db->query("SELECT * FROM {$this->table} WHERE {$fk} = {$id}");
        else
            $resultSQL = $this->db->query("SELECT * FROM {$this->table}");
        return $resultSQL->fetchAll(PDO::FETCH_ASSOC);
    }

    public function checkExists($id)
    {
        $resultSQL = $this->db->query("SELECT * FROM {$this->table} WHERE id = {$id}");
        $element = $resultSQL->fetch(PDO::FETCH_ASSOC);
        if ($element) {
            return true;
        } else {
            return false;
        }
    }

    public function save($element)
    {
        $result = 0;
        if ($this->checkExists($element["id"])) {
            $result = $this->update($element);
        } else {
            $result = $this->create($element);
        }
        if ($result != 1) {
            var_dump($result);
        }
    }

    public function update($element)
    {
        $columnsAndValues = SQLHelper::buildUpdate($element);

        $query = "UPDATE {$this->table} SET {$columnsAndValues} WHERE id = ?";

        $statement = $this->db->prepare($query);

        $data = ArrayHelper::buildUpdateData($element);

        $result = $statement->execute($data);

        //TODO find a better way to perform this if
        if ($result)
            return 1;
        else
            return $statement->errorInfo();
    }

    public function create($element)
    {
        $columns = SQLHelper::buildInsertColumns($element);
        $values = SQLHelper::buildInsertValues($element);

        $query = "INSERT INTO {$this->table} {$columns} VALUES {$values}";

        $statement = $this->db->prepare($query);

        $data = ArrayHelper::buildCreateData($element);

        $result = $statement->execute($data);

        if ($result)
            return $this->db->lastInsertId();
        else
            return $statement->errorInfo();
    }
}