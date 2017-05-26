package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import utils.Constant;
import utils.MessageProperties;
import dao.impl.UserDaoImpl;
import entity.User;

/**
 * Servlet implementation class ImageAdminController
 */
@WebServlet("/admin/image.ad")
public class ImageAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dir = "images";
		String fileName = "";
		int userId = 0;

		try {
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input
					// type="text|radio|checkbox|etc", select, etc).
					// String fieldName = item.getFieldName();
					String fieldValue = item.getString();

					userId = Integer.parseInt(fieldValue);
				} else {
					// Process form file field (input type="file").

					fileName = FilenameUtils.getName(item.getName());

					String uploadPath = getServletContext().getRealPath("")
							+ File.separator + dir;
					// System.out.println("location: " +location);
					// System.out.println("fieldName: " + fieldName);
					// System.out.println("fileName: " + fileName);

					// creates the directory if it does not exist
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					// saves the file on disk
					item.write(storeFile);

				}
			}
		} catch (FileUploadException e) {
			System.out.println("Error: Cannot parse multipart request.");
		} catch (Exception e) {
			System.out.println("Error: Cannot upload file.");
		}

		// cập nhật ảnh vào db
		if (!new UserDaoImpl().updateImage(dir + File.separator + fileName,userId)) {
			User user = new UserDaoImpl().getUserById(userId);
			request.setAttribute("messageErorr",
					MessageProperties.getData("ERR19"));
			request.setAttribute(Constant.ACTION, "hoso");
			request.setAttribute("name", user.getName());
			request.setAttribute("userId", user.getUserId());
			request.getRequestDispatcher("addUserSuccess.jsp").forward(request,
					response);
			return;
		}

		response.sendRedirect("info.ad");
		return;
	}

}
