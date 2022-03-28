import os
import matplotlib.pyplot as plt
import numpy as np

experiments = os.listdir('results')
assertions = set(map(lambda x: x.split('_')[0], experiments))

for ass in list(assertions):
    this_exp = list(filter(lambda x: x.startswith(f'{ass}_'), experiments))
    sizes = sorted(list(map(lambda x: int(x.split('_')[1].split('.')[0]), this_exp)))
    measurements = []
    means = []
    sds = []
    for size in sizes:
        with open(f'results/{ass}_{size}.csv') as f:
            m = list(map(lambda x: int(x.split(',')[1]), f.readlines()[1:]))
            if len(m) < 30:
                sizes = sizes[:-1]
                continue
            measurements.append(m)
            means.append(np.mean(m))
            sds.append(np.std(m))

    plt.errorbar(sizes, means, yerr=sds, marker='^', linestyle='None', capsize=5, ecolor='red')
    if '.strings.' in ass:
        plt.xlabel('Input size (number of characters)')
    else:
        plt.xlabel('Input size (number of entries)')
    plt.ylabel('Runtime (ns)')
    plt.title(f'{ass}')
    plt.tight_layout()
    plt.savefig(f'plots/{ass}.png')
    plt.clf()

