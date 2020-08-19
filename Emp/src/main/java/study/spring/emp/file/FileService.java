package study.spring.emp.file;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import study.spring.emp.file.model.FileVO;

@Service
public class FileService implements IFileService{

	@Autowired
	@Qualifier("IFileRepository")
	IFileRepository fileRepository;

	@Override
	public void uploadFile(FileVO file) {
		file.setFileId(fileRepository.getMaxFileId()+1);
		fileRepository.uploadFile(file);
	}

	@Override
	public FileVO getFile(int fileId) {
		return fileRepository.getFile(fileId);
	}

	@Override
	public List<FileVO> getAllFileList() {
		return fileRepository.getAllFileList();
	}

	@Override
	public void updateDirectory(int[] fileIds, String directoryName) {
		for (int fileId:fileIds) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("fileId", fileId);
			map.put("directoryName",directoryName);
			fileRepository.updateDirectory(map);
		}
	}

	@Override
	public List<FileVO> getFileListBydir(String directoryName) {
		return fileRepository.getFileListBydir(directoryName);
	}

	@Override
	public void deleteFile(int fileId) {
		fileRepository.deleteFile(fileId);
	}

	@Override
	public void deleteFile(String userId) {
		fileRepository.deleteFile(userId);
	}

	@Override
	public void updateFile(FileVO file) {
		fileRepository.updateFile(file);
	}

	@Override
	public void updateDirectory(HashMap<String, Object> map) {
		fileRepository.updateDirectory(map);
	}


	
	
}
