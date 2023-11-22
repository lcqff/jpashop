
package lotto.view;

public abstract class InputViewImpl<T> {
    public final T getInputUntilValid(String ErrorMessage) {
        while (true) {
            try {
                return getInput();
            } catch (NumberFormatException e) {
                System.out.println(ErrorMessage);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public abstract T getInput();
}
