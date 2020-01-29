package com.chaohappy.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    @RequestMapping(value="/spittles",method=RequestMethod.GET)
    public String listSpittlesForSpitter(@RequestParam("spitter") String username,Model model) {
        System.out.println("username："+username);
        model.addAttribute("spitter", username);
        List<String> list = new ArrayList<String>();
        list.add("spittle1");
        list.add("spittle2");
        model.addAttribute("spittles", list);
        return "list";

    }

    @RequestMapping(method=RequestMethod.GET,params="new")
    public String createSpitterProfile(Model model) {
        model.addAttribute("spitter", new Spitter());
        return "edit";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String addSpitterFromForm(@Valid Spitter spitter,BindingResult bindingResult,
                                     @RequestParam(value="image",required=false) MultipartFile image) {
        if(bindingResult.hasErrors()) {
            return  "edit";
        }
        if(!image.isEmpty()) {
            try {
                validateImage(image);
                saveImage(spitter.getFullName()+".jpg", image);
            } catch (Exception e) {
                bindingResult.reject(e.getMessage());
                e.printStackTrace();
                return  "edit";
            }

        }
        return "redirect:/spitter/"+spitter.getUsername();
    }

    private void validateImage(MultipartFile image) throws Exception {
        if(!image.getContentType().equals("image/jpeg")) {
            throw new Exception("只能上传格式为jpeg的文件");
        }
    }

    private void saveImage(String filename,MultipartFile image) {
        String webroot = System.getProperty("ebop-server.root");
        File file = new File(webroot+"/resources/"+filename);
        try {
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/{username}",method=RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username,Model model) {
        System.out.println("username："+username);
        model.addAttribute("spitter", username);
        List<String> list = new ArrayList<String>();
        list.add("spittle1");
        list.add("spittle2");
        model.addAttribute("spittles", list);
        return "list";
    }
}
