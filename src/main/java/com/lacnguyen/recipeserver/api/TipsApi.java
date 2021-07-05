package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.models.ResourceNotFoundException;
import com.lacnguyen.recipeserver.service.ITipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import com.lacnguyen.recipeserver.entity.TipsEntity;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@Api(value = "Tips APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tips")
public class TipsApi {

    @Autowired
    private ITipsService iTipsService;

    @GetMapping
    public ResponseEntity<Collection<TipsEntity>> getAll() {
        return new ResponseEntity<>(iTipsService.findTipsAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TipsEntity>> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iTipsService.findTipsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipsEntity> createTips(@RequestBody TipsEntity tipsEntity) {
        return new ResponseEntity<>(iTipsService.save(tipsEntity), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public TipsEntity updateTips(@PathVariable("id") Long id,
                                                 @Validated @RequestBody TipsEntity tipsEntity) {
        if (!iTipsService.isExist(id)) {
            throw new ResourceNotFoundException("Not found");
        }
        return iTipsService.findTipsById(id).map(tips -> {
            tips.setTitle(tipsEntity.getTitle());
            tips.setDescription(tipsEntity.getDescription());
            tips.setVideo(tipsEntity.getVideo());
            tips.setAuthor(tips.getAuthor());
            return iTipsService.save(tips);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Map<String, Object>> getAllCourse(@PathVariable(value = "id") Long id,
                                                            @RequestParam(value = "pageNumber") int pageNumber,
                                                            @RequestParam(value = "pageSize") int pageSize) {
        try {
            List<TipsEntity> tipsList = new ArrayList<>();
            Page<TipsEntity> tipsPage = iTipsService.findTipsAll(pageNumber, pageSize);
            tipsList = tipsPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("tips", tipsList);
            response.put("currentPage", tipsPage.getNumber());
            response.put("totalItems", tipsPage.getTotalElements());
            response.put("totalPages", tipsPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
