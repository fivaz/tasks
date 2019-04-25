<?php
/**
 * Created by PhpStorm.
 * UserDAO: Fivaz
 * Date: 05/02/2019
 * Time: 06:34
 */

class SQLHelper
{
    public static function buildInsertValues($attributes)
    {
        $values = "";
        foreach ($attributes as $key => $value)
            if (!is_array($value))
                $values .= "?, ";

        return SQLHelper::formatText($values);
    }

    public static function buildInsertColumns($attributes)
    {
        $columns = "";
        foreach ($attributes as $key => $value)
            if (!is_array($value))
                $columns .= $key . ", ";

        return SQLHelper::formatText($columns);
    }

    public static function formatText($text)
    {
        $text = substr($text, 0, strlen($text) - 2);

        return "(" . $text . ")";
    }

    public static function buildUpdate($attributes)
    {
        $columnsAndValues = "";
        foreach ($attributes as $key => $value)
            if (!is_array($value))
                $columnsAndValues .= $key . " = ?, ";

        return substr($columnsAndValues, 0, strlen($columnsAndValues) - 2);
    }

    public static function buildDelete($primary_keys)
    {
        $columnsAndValues = "";
        foreach ($primary_keys as $primary_key)
            $columnsAndValues .= $primary_key . " = ?, ";

        return substr($columnsAndValues, 0, strlen($columnsAndValues) - 2);
    }

}