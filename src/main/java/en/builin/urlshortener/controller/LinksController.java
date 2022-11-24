package en.builin.urlshortener.controller;

import en.builin.urlshortener.dto.LinkDto;
import en.builin.urlshortener.dto.OriginalDto;
import en.builin.urlshortener.service.LinksService;
import en.builin.urlshortener.service.StatsService;
import en.builin.urlshortener.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LinksController {

    private final LinksService linksService;
    private final StatsService statsService;

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public LinkDto generate(@Valid @RequestBody OriginalDto originalDto) {
        return linksService.generateLink(originalDto);
    }

    @GetMapping(WebUtils.LINKS_ENDPOINT + "{shortLink}")
    public void redirect(@PathVariable("shortLink") String shortLink, HttpServletResponse httpServletResponse) {

        httpServletResponse.setHeader("Location", linksService.originalByShortLink(shortLink).getOriginal());
        httpServletResponse.setStatus(302);

        statsService.countRedirect(shortLink);
    }
}
