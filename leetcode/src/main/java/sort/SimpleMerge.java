package sort;

/**
 * Created on 2021-04-05
 */
class SimpleMerge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // move to end
        if (m>0) {
            for (int i=m+n-1;i>=m;i--) {
                nums1[i] = nums1[i-n];
            }
        }

        //System.out.println(Arrays.toString(nums1));
        int i = m, j = 0, k = 0;
        while (i<m+n || j<n) {
            if (i>=m+n) {
                nums1[k++] = nums2[j++];
                continue;
            }
            if (j>=n) {
                nums1[k++] = nums1[i++];
                continue;
            }
            if (nums1[i]<=nums2[j]) {
                nums1[k++] = nums1[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }

    }

    public static void main(String[] args) {
        new SimpleMerge().merge(new int[1], 0, new int[]{1}, 1);
    }
}
