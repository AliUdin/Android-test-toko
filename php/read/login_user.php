<?php 
	include '../koneksi.php';

	//buat var
    $code = "code";
	$message = "message";
	//buat var respone array untuk menampung nilai dari db
    $response = array();

	//cek apakah dia post
	if ($_SERVER['REQUEST_METHOD'] == 'POST') {
		$password = $_POST['password'];
		$nama = $_POST['nama_user'];
		$kategori = $_POST['kategori'];

		$query = " SELECT * FROM user WHERE (password ='$password' AND nama_user ='$nama') AND kategori = '$kategori' ";
		//koneksi yuk
		$result = mysqli_query($con,$query);
		//cek apakah ada akun					

			if(mysqli_num_rows($result) === 0 )  {   
		      	// data tidak ditemukan, buat pesan error
		      	//tadi kurang echo
		      	echo json_encode(
		      		array( $code => 404, $message=> 'nama dan password tidak ditemukan')
		      	);	     
		  	}else{
		  		//loping data untuk mengambil nilainya
		  		while ($data = mysqli_fetch_assoc($result)) {
		  			//masukan data ke array 2 dimensi
		  			array_push( $response, array(
						"id_user" => $data["id_user"],
						"nama" => $data["nama_user"],
						"jenkel" => $data["jenkel"],
						"no_hp" => $data["no_hp"],
						"password" => $data ["password"],
						"alamat" => $data["alamat"],
						"kategori" => $data["kategori"]

		  			));
		  		}
		  		// $response[0]['nama'] ambil nilai dari index nol dengan assoc array key 'nama'
		  		echo json_encode(
		  			array( $code => 200, $message => 'selamat datang '.$response[0]['nama'], 
		  				'user' => $response)
		  		);
		  	}
	  	 }else{
    			echo json_encode(
    				array( $code => 101, $message => 'request tidak valid')
    			);
    }
 ?>