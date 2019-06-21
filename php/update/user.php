<?php 
	include '../koneksi.php';
	$query = "";
	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		$id = $_POST['id_user'];
		$nama = $_POST['nama_user'];
		$pass = $_POST['password'];
		$alamat = $_POST['alamat'];
		$kategori = $_POST['kategori'];
		$jenkel = $_POST['jenkel'];
		$no_hp = $_POST['no_hp'];

		$query = "UPDATE user SET nama_user='$nama',  sandi='$pass', alamat='$alamat',kategori='$kategori', jenkel ='$jenkel', no_hp= $no_hp WHERE id_user='$id'";
		
		$exeQuery = mysqli_query($con, $query);

		echo ($exeQuery) ? json_encode(array('code' =>200, 'message' => 'Data berhasil ubah')) : json_encode(array('code' =>400, 'message' => 'data gagal diubah'));
	}	else {
		echo json_encode(array('code' =>404, 'message' => 'request tidak valid'));
		}
 ?>