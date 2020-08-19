package study.spring.emp.file;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import study.spring.emp.file.model.FileVO;

public interface IFileRepository {

	public int getMaxFileId();
	public void uploadFile(FileVO file);
	public FileVO getFile(@Param("fileId") int fileId);
	public List<FileVO> getAllFileList();
	public List<FileVO> getFileListBydir(@Param("directoryName") String directoryName);
	public void deleteFile(@Param("fileId") int fileId);
	public void deleteFile(@Param("userId") String userId);
	public void updateFile(FileVO file);
	public void updateDirectory(HashMap<String, Object> map);
	
}
