package football_app.searcher;

import java.util.List;

interface ClubSearcher {
    List<ClubLinkDto> findClubsByClubName(String clubName);
}
