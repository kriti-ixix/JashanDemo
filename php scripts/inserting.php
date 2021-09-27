<?php

$name = $_POST['name'];
$rollno = $_POST['rollno'];
$class = $_POST['class'];
$marks = $_POST['marks'];

#mysqli_connect(server ip address, username, password, database)
$connection = mysqli_connect("localhost", "root", "", "jashan");

if ($connection)
{
    echo "Connection successful <br>";
    $query = "insert into student values('$name', $rollno, $class, $marks)";
    //$query = "update students set marks=50 where rollno=1";
    $result = mysqli_query($connection, $query);

    if ($result == 0)
    {
        echo "Values not inserted successfully";
    }
    else
    {
        echo "Values inserted successfully";
    }
}
else
{
    echo "Error connecting";
}

?>