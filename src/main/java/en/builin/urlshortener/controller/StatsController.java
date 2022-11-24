package en.builin.urlshortener.controller;

import en.builin.urlshortener.dto.StatView;
import en.builin.urlshortener.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
@Validated
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<StatView> getStats(@RequestParam("page") int page, @RequestParam("count") @Min(1) @Max(100) int count) {
        return statsService.getStatViews(page, count);
    }

    @GetMapping("/{shortLink}")
    public StatView getStat(@PathVariable("shortLink") String shortLink) {
        return statsService.getStatView(shortLink);
    }
}
