package com.tj.ex.service.IllustService;

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
import com.tj.ex.dao.IllustDao;
import com.tj.ex.service.MemberService.Service;

public class IllustWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("FileBoardUp/5_IllustUp");
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mRequest = null;
		String iFilename = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			iFilename = mRequest.getFilesystemName(param);
			HttpSession httpSession = request.getSession();
			String mId = (String) httpSession.getAttribute("mId");
			String iTitle = mRequest.getParameter("iTitle");
			String iContent = mRequest.getParameter("iContent");
			String iPw = mRequest.getParameter("iPw");
			IllustDao Dao = new IllustDao();
			int result = Dao.write(mId, iTitle, iContent, iFilename, iPw);
			
			if (result == IllustDao.SUCCESS) {
				request.setAttribute("resultMsg", "글쓰기 성공");
			} else {
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
		File serverFile = new File(path + "/" + iFilename);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/6_JSP/MyProject/WebContent/FileBoardUp/5_IllustUp/" + iFilename);
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