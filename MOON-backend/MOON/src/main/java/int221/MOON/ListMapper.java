package int221.MOON;

import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private ListMapper() {}

    public <S,T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper mapper) {
        return source.stream().map(entity -> mapper.map(entity, targetClass)).collect(Collectors.toList());
    }

    public static ListMapper getInstance() {
        return listMapper;
    }

}
