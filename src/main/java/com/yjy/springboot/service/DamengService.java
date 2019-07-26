package com.yjy.springboot.service;

import java.util.List;

import com.yjy.springboot.model.CLinfoModel;
import com.yjy.springboot.model.DMDriverInfo;

public interface DamengService {

	List<CLinfoModel> getCarInfoByNo(String hphm, String hPZL);

	List<DMDriverInfo> getDriverInfoById(String sfzhm);


}
