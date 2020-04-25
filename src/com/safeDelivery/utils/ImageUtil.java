package com.safeDelivery.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

public class ImageUtil {
	private static ByteArrayOutputStream baos = null;
	private static PreparedStatement preparedStatement = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static FileOutputStream fileOuputStream = null;

	public static long uploadImage(Connection connection, File file, long id_plat) {
		try {

//			File file = new File("file:///"+image);
			if(!file.canRead()) {
				System.out.println("Total space "+file.exists());
				return -1;
			}
			BufferedImage originalImage = ImageIO.read(file);
			String image_name = file.getName();

			baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();

			String insertImageSql = "INSERT INTO " + "imagetable(image_data, image_name, id_plat)" + " VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(insertImageSql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setBytes(1, imageInByte);
			preparedStatement.setString(2, image_name);
			preparedStatement.setLong(3, id_plat);

			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			baos.close();
//			fileOuputStream.close();
			if (rs.next()) {
				System.out.println("Image sucessfully inserted to the table");
				int rsgetint = rs.getInt(1);
				preparedStatement.close();
				return rsgetint;
			}
			else {
				preparedStatement.close();
				return -2;
			}
			
			
		} catch (Exception e) {
			System.out.println("Failed to make connection!" + e);
			e.printStackTrace();
			return -3;
		}
	}

	public static String DownloadImage(Connection connection, long id_plat) {
		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM imagetable where id_plat = "+id_plat);

			String imageName = "";
			byte[] image = null;
			int id = 0;

			if (resultSet.next()) {
				id = resultSet.getInt(1);
				image = resultSet.getBytes(2);
				imageName = resultSet.getString(3);

				statement.close();
//			 convert array of bytes into modified file again
				String path = "resources/images/"+imageName;
				File imgFile = new  File(path);
				fileOuputStream = new FileOutputStream(imgFile);
				fileOuputStream.write(image);
				
				fileOuputStream.close();
				
				System.out.println("\nImage successully retrieved " + "from database. Id of image: " + id + " Image name: "
						+ imageName);
				System.out.println("Path "+imgFile.getAbsolutePath());
				return "file:///"+imgFile.getAbsolutePath();
			}
			else {
				return null;
			}
			

		} catch (Exception e) {
			System.out.println("Failed to make connection!" + e);
			e.printStackTrace();
			return null;
		}
	}
	
	public static int deleteImage(Connection connection, long id) {
		try {
			if (connection != null) {
				String query = "select count(*) from imagetable where id_plat = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				result.next();
				System.out.println("Recherche si il existe déjà une image "+result.getInt(1));
				if (result.getInt(1) == 1) {
					query = "delete from imagetable where id_plat = ?";
					ps = connection.prepareStatement(query);
					ps.setLong(1, id);
					int count = ps.executeUpdate();
					if (count > 0) {
						ps.close();
						return 1;
					} else {
						ps.close();
						return -1;
					}
				} else {
					ps.close();
					return 0;
				}
				
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}
	}

	public static void main(String[] args) {
		
//		Connection connection = SingletonConnexion.startConnection();
		
//		File file = new File("C:\\Users\\besto\\Downloads\\Images\\food-5.jpg");
		
//		uploadImage(connection, file, 1);
		
//		DownloadImage(connection, 1);
		
	}

}
