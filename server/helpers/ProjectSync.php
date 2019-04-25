<?php

//require_once("../dao/ProjectDAO.php");

class ProjectSync
{
    static function upload($project_array)
    {
        $dao = new ProjectDAO();
        $dao->save($project_array);
        foreach ($project_array["listts"] as $listt_array)
            ListtSync::sync($listt_array);
    }

    public static function download($user_array)
    {
        $dao = new ProjectDAO();
        $projects = $dao->findAll("user_id", $user_array["id"]);
        $user_array["projects"] = $projects;

        $projects_array = array();
        foreach ($user_array["projects"] as $project_array) {
            $project_array = ListtSync::download($project_array);
            array_push($projects_array, $project_array);
        }
        $user_array["projects"] = $projects_array;

        return $user_array;
    }
}