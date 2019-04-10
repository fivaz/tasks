<?php

//require_once("../dao/ListtDAO.php");

class ListtSync
{
    static function upload($listt_array)
    {
        $dao = new ListtDAO();
        $dao->save($listt_array);
    }

    public static function download($project_array)
    {
        $dao = new ListtDAO();
        $listts = $dao->findAll("project_id", $project_array["id"]);
        $project_array["listts"] = $listts;

        /*
        $projects_array = array();

        foreach ($user_array["projects"] as $project_array) {
            $project_array = ListtSync::download($project_array);
            array_push($projects_array, $project_array);
        }
        $user_array["projects"] = $projects_array;
        */

        return $project_array;
    }
}