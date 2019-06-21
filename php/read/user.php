<?php
//koneksi
// definisikan koneksi ke database
 include '../koneksi.php';

    $sql = "";
    $sql=mysqli_query($con,"SELECT * FROM user ORDER BY id_user DESC");  
    //query untuk menampilkan semua data ditable
    // $sql=mysqli_query($con,"SELECT * FROM jadwal_tranfusi ORDER BY id_tranfusi desc");
    //untuk menampung isi data
    $response=array();
    $cek=mysqli_num_rows($sql);
    if($cek >0){
        $response["data_user"]=array();
        //perulangan
        while ($row=mysqli_fetch_array($sql)){
            $data=array();
            $data["id_user"]=$row["id_user"];
            $data["nama"]=$row["nama_user"];
            $data["jenkel"]=$row["jenkel"];
            $data["no_hp"]=$row["no_hp"];
            $data["kategori"]=$row["kategori"];
            $data["password"]=$row["password"];
            $data["alamat"]=$row["alamat"];
           


            $response["pesan"]="berhasil Mengambil Data";
            $response["response"]="true";    
            array_push($response["data_user"],$data);
            // print_r($row);
        }
        //mengubah data menjadi JSON
        echo json_encode($response);
    }else{
        $response["pesan"]="Gagal Mengambil Data";
        $response["response"]="false";
        echo json_encode($response);
    } 

?>