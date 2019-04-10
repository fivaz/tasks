<?php
require_once("global.php");

$json = '
{
  "user": {
    "id": 1,
    "first_name": "ab",
    "last_name": "a",
    "email": "a",
    "password": "a",
    "projects": [
      {
        "id": 3,
        "name": "DMOB1",
        "start_at": "2019-04-10 15:58:00",
        "end_at": "2019-04-10 16:58:00",
        "isArchived": false,
        "user_id": 1,
        "listts": [
          {
            "id": 7,
            "name": "TODO",
            "position": 0,
            "project_id": 3,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 8,
            "name": "DOING",
            "position": 1,
            "project_id": 3,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 9,
            "name": "DONE",
            "position": 2,
            "project_id": 3,
            "isDone": true,
            "isArchived": false
          }
        ]
      },
      {
        "id": 4,
        "name": "sadsad",
        "start_at": "2019-04-10 16:01:00",
        "end_at": "2019-04-10 17:01:00",
        "isArchived": false,
        "user_id": 1,
        "listts": [
          {
            "id": 10,
            "name": "TODO",
            "position": 0,
            "project_id": 4,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 11,
            "name": "DOING",
            "position": 1,
            "project_id": 4,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 12,
            "name": "DONE",
            "position": 2,
            "project_id": 4,
            "isDone": true,
            "isArchived": false
          }
        ]
      },
      {
        "id": 5,
        "name": "sad",
        "start_at": "2019-04-10 16:02:00",
        "end_at": "2019-04-10 17:02:00",
        "isArchived": false,
        "user_id": 1,
        "listts": [
          {
            "id": 13,
            "name": "TODO",
            "position": 0,
            "project_id": 5,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 14,
            "name": "DOING",
            "position": 1,
            "project_id": 5,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 15,
            "name": "DONE",
            "position": 2,
            "project_id": 5,
            "isDone": true,
            "isArchived": false
          }
        ]
      },
      {
        "id": 6,
        "name": "ads",
        "start_at": "2019-04-10 16:03:00",
        "end_at": "2019-04-10 17:03:00",
        "isArchived": false,
        "user_id": 1,
        "listts": [
          {
            "id": 16,
            "name": "TODO",
            "position": 0,
            "project_id": 6,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 17,
            "name": "DOING",
            "position": 1,
            "project_id": 6,
            "isDone": false,
            "isArchived": false
          },
          {
            "id": 18,
            "name": "DONE",
            "position": 2,
            "project_id": 6,
            "isDone": true,
            "isArchived": false
          }
        ]
      }
    ]
  }
}
';

$json2 = '
{
  "user": {
    "id": 1,
    "first_name": "ab",
    "last_name": "a",
    "email": "a",
    "password": "a",
    "projects": []
  }
}
';

$array = json_decode($json2, true);

$array['user'] = UserSync::sync($array['user']);

$json = json_encode($array);

echo $json;