package com.tj.ex.service.TechniqueService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.AnimalDao;
import com.tj.ex.service.MemberService.Service;

public class TechniqueWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("FileBoardUp/1_AnimalUp");
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mRequest = null;
		String aFilename = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			aFilename = mRequest.getFilesystemName(param);
			HttpSession httpSession = request.getSession();
			String mId = (String) httpSession.getAttribute("mId");
			String aTitle = mRequest.getParameter("aTitle");
			String aContent = mRequest.getParameter("aContent");
			String aPw = mRequest.getParameter("aPw");
			AnimalDao Dao = new AnimalDao();
			int result = Dao.write(mId, aTitle, aContent, aFilename, aPw);
			
			if (result == AnimalDao.SUCCESS) {
				request.setAttribute("resultMsg", "글쓰기 성공");
			} else {
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
		File serverFile = new File(path + "/" + aFilename);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/6_JSP/MyProject/WebContent/FileBoardUp/1_AnimalUp/" + aFilename);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int nByteCnt = is.read(bs);
					if (nByteCnt == -1)
						break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (os != null)
						os.close();
					if (is != null)
						is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}