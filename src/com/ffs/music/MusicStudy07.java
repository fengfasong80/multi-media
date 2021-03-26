package com.ffs.music;

import biz.source_code.dsp.math.Complex;
import biz.source_code.dsp.transform.Dft;

/**
 * 傅里叶变换
 * @author fengfasong
 * @date 2020/9/28
 */
public class MusicStudy07 {
    public static void main(String[] args) {
        double[] temp = {1.0,2.0,3.0,5.0,0.1,0.3,0.5,0.2};
        Complex[] complexes = Dft.goertzelSpectrum(temp);
        for(int i = 0;i<complexes.length;i++){
            System.out.print(complexes[i].abs());
            System.out.print("    ");
            System.out.print(complexes[i].arg());
            System.out.println();
        }
    }

}
