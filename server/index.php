<?php
require_once("helpers/ArrayHelper.php");
require_once("helpers/SQLHelper.php");
require_once("dao/Project.php");
require_once("dao/Listt.php");

$projectDAO = new Project();
$listtDAO = new Listt();

//$json = file_get_contents('php://input');

$json = '{
            "project":{
                "id":1,
                "name":"DMOB",
                "start_at":"2019-04-10 10:53:00",
                "end_at":"2019-04-10 10:56:00",
                "isArchived":false,
                "listts":[
                    {"id":1,"name":"TODO","position":0,"project_id":1,"isDone":false,"isArchived":false},
                    {"id":2,"name":"DOING","position":1,"project_id":1,"isDone":false,"isArchived":false},
                    {"id":3,"name":"DONE","position":2,"project_id":1,"isDone":true,"isArchived":false},
                    {"id":4,"name":"TEST","position":3,"project_id":1,"isDone":false,"isArchived":false}
                ]
            }
        }';

$json_array = json_decode($json, true);

$project = $json_array['project'];

$projectDAO->save($project);

foreach ($project['listts'] as $listt){
    $listtDAO->save($listt);
}