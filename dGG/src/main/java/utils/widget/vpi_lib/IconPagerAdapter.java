package utils.widget.vpi_lib;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    String getIconUrl(int index);

    // From PagerAdapter
    int getCount();
}
