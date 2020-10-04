package football_app.searcher;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
class SearchController {

    private final ClubSearcher clubSearcher;

    @GetMapping("/{clubName}")
    List<ClubLinkDto> getClubLinksByClubName(@PathVariable @Valid @NotBlank String clubName) {
        return clubSearcher.findClubsByClubName(clubName);
    }
}