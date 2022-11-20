package en.builin.urlshortener.controller;

import en.builin.urlshortener.dto.LinkDto;
import en.builin.urlshortener.dto.OriginalDto;
import en.builin.urlshortener.service.LinksService;
import en.builin.urlshortener.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LinksController {

    private final LinksService service;

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public LinkDto createPerson(@Valid @RequestBody OriginalDto originalDto) {
        return service.generateLink(originalDto);
    }

    @GetMapping(WebUtils.LINKS_ENDPOINT + "{key}")
    public void getUrl(@PathVariable("key") String key, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", service.getLink(key).getOriginal());
        httpServletResponse.setStatus(302);
    }
}
