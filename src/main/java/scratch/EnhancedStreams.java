package scratch;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.Arrays.asList;

public class EnhancedStreams {

    public static void main(String... args) {
        EnhancedList<String> input = () -> asList("foo", "bar");
        List<String> result = input
                .stream()
                .filter(s -> s.startsWith("f"))
                .flatMapCollection(EnhancedStreams::threeOf)
        .toList();


        result.forEach(System.out::println);

    }

    public static List<String> threeOf(String input) {
        return asList(input, input, input);
    }


    interface EnhancedList<T> extends ForwardingList<T> {
        default EnhancedStream<T> stream() {
            return () -> ForwardingList.super.stream();
        }
    }

    interface EnhancedStream<T> extends ForwardingStream<T> {
        default List<T> toList() {
            return collect(Collectors.toList());
        }

        default <R> EnhancedStream<R> flatMapCollection(Function<? super T, ? extends Collection<? extends R>> mapper) {
            return flatMap(mapper.andThen(Collection::stream));
        }

        default EnhancedStream<T> filter(Predicate<? super T> predicate) {
            return () -> ForwardingStream.super.filter(predicate);
        }

        default <R> EnhancedStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
            return () -> ForwardingStream.super.flatMap(mapper);
        }

        default EnhancedStream<T> sequential() {
            return () -> ForwardingStream.super.sequential();
        }

        default EnhancedStream<T> parallel() {
            return () -> ForwardingStream.super.parallel();
        }

        default EnhancedStream<T> skip(long n) {
            return () -> ForwardingStream.super.skip(n);
        }

        default EnhancedStream<T> sorted(Comparator<? super T> comparator) {
            return () -> ForwardingStream.super.sorted(comparator);
        }

        default EnhancedStream<T> sorted() {
            return () -> ForwardingStream.super.sorted();
        }

        default <R> EnhancedStream<R> map(Function<? super T, ? extends R> mapper) {
            return () -> ForwardingStream.super.map(mapper);
        }

        default EnhancedStream<T> peek(Consumer<? super T> action) {
            return () -> ForwardingStream.super.peek(action);
        }

        default EnhancedStream<T> distinct() {
            return () -> ForwardingStream.super.distinct();
        }

        default EnhancedStream<T> unordered() {
            return () -> ForwardingStream.super.unordered();
        }

        default EnhancedStream<T> onClose(Runnable closeHandler) {
            return () -> ForwardingStream.super.onClose(closeHandler);
        }

        default EnhancedStream<T> limit(long maxSize) {
            return () -> ForwardingStream.super.limit(maxSize);
        }
    }

    interface ForwardingStream<T> extends Stream<T> {
        Stream<T> delegate();

        default Stream<T> filter(Predicate<? super T> predicate) {
            return delegate().filter(predicate);
        }

        default Object[] toArray() {
            return delegate().toArray();
        }

        default boolean isParallel() {
            return delegate().isParallel();
        }

        default boolean allMatch(Predicate<? super T> predicate) {
            return delegate().allMatch(predicate);
        }

        default IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
            return delegate().flatMapToInt(mapper);
        }

        default Iterator<T> iterator() {
            return delegate().iterator();
        }

        default IntStream mapToInt(ToIntFunction<? super T> mapper) {
            return delegate().mapToInt(mapper);
        }

        default <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
            return delegate().flatMap(mapper);
        }

        default void forEach(Consumer<? super T> action) {
            delegate().forEach(action);
        }

        default DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
            return delegate().flatMapToDouble(mapper);
        }

        default Stream<T> sequential() {
            return delegate().sequential();
        }

        default void close() {
            delegate().close();
        }

        default Stream<T> parallel() {
            return delegate().parallel();
        }

        default LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
            return delegate().flatMapToLong(mapper);
        }

        default Stream<T> skip(long n) {
            return delegate().skip(n);
        }

        default <A> A[] toArray(IntFunction<A[]> generator) {
            return delegate().toArray(generator);
        }

        default Optional<T> max(Comparator<? super T> comparator) {
            return delegate().max(comparator);
        }

        default Stream<T> sorted(Comparator<? super T> comparator) {
            return delegate().sorted(comparator);
        }

        default LongStream mapToLong(ToLongFunction<? super T> mapper) {
            return delegate().mapToLong(mapper);
        }

        default Spliterator<T> spliterator() {
            return delegate().spliterator();
        }

        default Optional<T> findAny() {
            return delegate().findAny();
        }

        default <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
            return delegate().reduce(identity, accumulator, combiner);
        }

        default long count() {
            return delegate().count();
        }

        default Optional<T> reduce(BinaryOperator<T> accumulator) {
            return delegate().reduce(accumulator);
        }

        default <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
            return delegate().collect(supplier, accumulator, combiner);
        }

        default <R, A> R collect(Collector<? super T, A, R> collector) {
            return delegate().collect(collector);
        }

        default Stream<T> sorted() {
            return delegate().sorted();
        }

        default <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
            return delegate().map(mapper);
        }

        default Stream<T> peek(Consumer<? super T> action) {
            return delegate().peek(action);
        }

        default Stream<T> distinct() {
            return delegate().distinct();
        }

        default boolean anyMatch(Predicate<? super T> predicate) {
            return delegate().anyMatch(predicate);
        }

        default Stream<T> unordered() {
            return delegate().unordered();
        }

        default Optional<T> min(Comparator<? super T> comparator) {
            return delegate().min(comparator);
        }

        default Stream<T> onClose(Runnable closeHandler) {
            return delegate().onClose(closeHandler);
        }

        default void forEachOrdered(Consumer<? super T> action) {
            delegate().forEachOrdered(action);
        }

        default DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
            return delegate().mapToDouble(mapper);
        }

        default boolean noneMatch(Predicate<? super T> predicate) {
            return delegate().noneMatch(predicate);
        }

        default Stream<T> limit(long maxSize) {
            return delegate().limit(maxSize);
        }

        default T reduce(T identity, BinaryOperator<T> accumulator) {
            return delegate().reduce(identity, accumulator);
        }

        default Optional<T> findFirst() {
            return delegate().findFirst();
        }
    }

    interface ForwardingList<T> extends List<T> {
        List<T> delegate();

        default int size() {
            return delegate().size();
        }

        default T get(int index) {
            return delegate().get(index);
        }

        default boolean contains(Object o) {
            return delegate().contains(o);
        }

        default ListIterator<T> listIterator() {
            return delegate().listIterator();
        }

        default <T1> T1[] toArray(T1[] a) {
            return delegate().toArray(a);
        }

        default Spliterator<T> spliterator() {
            return delegate().spliterator();
        }

        default boolean removeIf(Predicate<? super T> filter) {
            return delegate().removeIf(filter);
        }

        default boolean removeAll(Collection<?> c) {
            return delegate().removeAll(c);
        }

        default boolean remove(Object o) {
            return delegate().remove(o);
        }

        default T remove(int index) {
            return delegate().remove(index);
        }

        default int indexOf(Object o) {
            return delegate().indexOf(o);
        }

        default Iterator<T> iterator() {
            return delegate().iterator();
        }

        default void forEach(Consumer<? super T> action) {
            delegate().forEach(action);
        }

        default int lastIndexOf(Object o) {
            return delegate().lastIndexOf(o);
        }

        default T set(int index, T element) {
            return delegate().set(index, element);
        }

        default boolean addAll(Collection<? extends T> c) {
            return delegate().addAll(c);
        }

        default Object[] toArray() {
            return delegate().toArray();
        }

        default void replaceAll(UnaryOperator<T> operator) {
            delegate().replaceAll(operator);
        }

        default boolean containsAll(Collection<?> c) {
            return delegate().containsAll(c);
        }

        default List<T> subList(int fromIndex, int toIndex) {
            return delegate().subList(fromIndex, toIndex);
        }

        default void add(int index, T element) {
            delegate().add(index, element);
        }

        default boolean add(T t) {
            return delegate().add(t);
        }

        default void sort(Comparator<? super T> c) {
            delegate().sort(c);
        }

        default void clear() {
            delegate().clear();
        }

        default boolean addAll(int index, Collection<? extends T> c) {
            return delegate().addAll(index, c);
        }

        default boolean isEmpty() {
            return delegate().isEmpty();
        }

        default boolean retainAll(Collection<?> c) {
            return delegate().retainAll(c);
        }

        default Stream<T> stream() {
            return delegate().stream();
        }

        default Stream<T> parallelStream() {
            return delegate().parallelStream();
        }

        default ListIterator<T> listIterator(int index) {
            return delegate().listIterator(index);
        }
    }
}

