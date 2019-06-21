<?php 
	include '../koneksi.php';

	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		
		$nama = $_POST['nama'];
		$jenkel = $_POST['jenkel'];
		$no_hp = $_POST['no_hp'];
		$pass = $_POST['password'];
		$alamat = $_POST['alamat'];
		$kategori = $_POST['kategori'];
	
		
	
        //query untuk menambahkan data
		$query = "INSERT INTO user VALUES (null, '$nama', '$jenkel','$no_hp', '$pass', 
			'$alamat','$kategori')";

		$exeQuery = mysqli_query($con, $query);

		echo ($exeQuery) ? json_encode(array('code' => 200, 'message' => 'Data berhasil disimpan')) : json_encode(array('code' => 400, 'message' => 'data gagal disimpan'));
	} else{
		echo json_encode(array('code' => 404, 'message' => 'request tidak valid'));
	}
 ?>