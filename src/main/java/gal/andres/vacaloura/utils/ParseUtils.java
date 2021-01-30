package gal.andres.vacaloura.utils;

import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseUtils {

  private ParseUtils() {}

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
                .map(ParseUtils::getSortFromParameter)
                .collect(Collectors.toList()))
        : Sort.by(Sort.Order.desc("id"));
  }

  /**
   * Build a Order from a query parameter
   *
   * @param parameter {@link String} with a - if the sorting order is descendant
   * @return {@link org.springframework.data.domain.Sort.Order} of the parameter with the right
   *     orientation
   */
  private static Sort.Order getSortFromParameter(String parameter) {
    if (isDescendant(parameter)) {
      return Sort.Order.desc(parameter.substring(1, parameter.length()));
    } else {
      return Sort.Order.asc(parameter);
    }
  }

  private static boolean isDescendant(String parameter) {
    return parameter.charAt(0) == '-';
  }
}
