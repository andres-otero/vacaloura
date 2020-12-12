package gal.andres.vacaloura.utils;

import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseUtils {

  /**
   * Build a {@link Sort} object from a list of attributes
   *
   * @param query {@link String} List of the attributes separated by commas and preceded by an - if
   *     the sort order is descending
   * @return {@link Sort} of the attributes established by the query in the right order. If there is
   *     no query the default sorting is by id
   */
  public static Sort getSortFromQuery(String query) {
    return (query != null && !query.isEmpty())
        ? Sort.by(
            Stream.of(query.split(","))
                .map(
                    s ->
                        (s.charAt(0) == '-')
                            ? Sort.Order.desc(s.substring(1, s.length()))
                            : Sort.Order.asc(s))
                .collect(Collectors.toList()))
        : Sort.by(Sort.Order.desc("id"));
  }
}
