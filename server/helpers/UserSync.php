<?php

//require_once("../dao/UserDAO.php");

class UserSync
{
    static function sync($user_array)
    {
        $dao = new UserDAO();
        $dao->save($user_array);

        if (count($user_array["projects"]) == 0) {
            $user_array = ProjectSync::download($user_array);
        } else {
            foreach ($user_array["projects"] as $project_array) {
                ProjectSync::upload($project_array);
            }
        }

        return $user_array;
    }
}