<?php
//koneksi
// definisikan koneksi ke database
 include '../koneksi.php';
    //query untuk menampilkan semua data ditable
    $sql = mysqli_query($con,"SELECT max(id_user) as maxKode FROM user");
    //untuk menampung isi data
    $data = mysqli_fetch_array($sql);
    $nomerDaftar = $data['maxKode'];
    $nomerDaftar = $nomerDaftar + 1;
    //echo $nomerDaftar;
    //untuk menampung isi data
    $response = array();
    $cek = mysqli_num_rows($sql);
    if($cek >0){
        $response=array();
        $response["no_urut"] = "user-".$nomerDaftar;
        $response["pesan"] ="berhasil Mengambil Data";
        $response["sukses"] ="true";    
        //mengubah data menjadi JSON
        echo json_encode($response);
    }else{
        $response["pesan"] ="Gagal Mengambil Data";
        $response["sukses"] ="false";
        echo json_encode($response);
    } 
?>