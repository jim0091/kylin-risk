package com.rkylin.risk.boss.controller;

import com.rkylin.risk.core.entity.DictionaryCode;
import com.rkylin.risk.core.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by v-wangwei on 2015/9/14 0014.
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryAction {
  @Resource
  private DictionaryService dictionaryService;

  @RequestMapping("toQueryDictionary")
  public ModelAndView toQueryDictionary() {
    return new ModelAndView("dictionary/dictionaryQuery");
  }

  @RequestMapping("toAjaxAddDictionary")
  public ModelAndView toAddDictionary() {
    return new ModelAndView("dictionary/dictionaryAdd");
  }

  @RequestMapping("toAjaxDictionaryDetail")
  public ModelAndView updateDictionary(String id) {
    ModelAndView view = new ModelAndView("dictionary/dictionaryModify");
    DictionaryCode dictionaryCode = dictionaryService.queryById(id);
    view.addObject("dictionaryCode", dictionaryCode);
    return view;
  }
}
