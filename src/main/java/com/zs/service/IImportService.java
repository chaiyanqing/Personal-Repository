package com.zs.service;

import java.util.ArrayList;
import java.util.List;

public interface IImportService {
	List<String> importMemberMuiltiSimple(List<ArrayList<String> >list, String groupName, Integer fromIndex);
	List<String> importHardwareMuiltiSimple(List<ArrayList<String> >list, String groupName, Integer fromIndex);
}
