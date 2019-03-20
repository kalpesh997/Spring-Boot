package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@Controller
public class AlienController {

	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien a)
	{
		repo.save(a);
		return "home.jsp";
	}
	
	@RequestMapping("/aliens")
	@ResponseBody
	public List<Alien> alien()
	{			
		return repo.findAll();
	}
	
	@DeleteMapping("/aliens/{aid}")
	@ResponseBody 
	public String alienDel(@PathVariable int aid)
	{			
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return "deleted";	
	}
	
	@RequestMapping("/aliens/{aid}")
	@ResponseBody
	public Optional<Alien> alien1(@PathVariable int aid)
	{			
		return repo.findById(aid);	
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid)
	{	
		ModelAndView mv = new ModelAndView("show.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		
		System.out.print(repo.findByTech("java"));
		//System.out.print(repo.findByAidGraterthan(102));
		//System.out.print(repo.findByTechSorted("java"));
		
		mv.addObject(alien);
		return mv;
	}
			
}
